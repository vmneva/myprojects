require('dotenv').config()  
const express = require('express')
const app = express()
var morgan = require('morgan')
const cors = require('cors')

const Person = require('./models/person')

app.use(cors())
app.use(express.json())
app.use(express.static('build'))
app.use(morgan('tiny'))   
morgan.token('host', function(req, res) {
    return req.hostname;
});

const errorHandler = (error, request, response, next) => {
  console.error(error.message)

  if (error.name === 'CastError') {
    return response.status(400).send({ error: 'malformatted id' })
  }
  next(error)
}
app.use(errorHandler) 

let persons = [
    {
      id: 1,
      name: "Arto Hellas",
      number: "040-123456"
    },
    {
      id: 2,
      name: "Ada Lovelace",
      number: "39-44-5323523"
    },
    {
      id: 3,
      name: "Dan Abramov",
      number: "12-43-234345"
    },
    {
      id: 4,
      name: "Mary Poppendieck",
      number: "39-23-6423122"
    }
  ]
  
  app.get('/api/persons', (req, res) => {
    Person.find(req.params).then(persons => { 
      res.json(persons)
    })
  })
  
  app.get('/info', (req, res) => {
    res.send(
        `<p>Phonebook has info for ${persons.length} people</p>
        <p>${new Date()}</p>`)
  })
  
  app.get('/api/persons/:id', (request, response, next) => {
    Person.findById(request.params.id)
    .then(person => {
      if (person) {
        response.json(person)
      } 
      else {  
        response.status(404).end()
      }
    })
    .catch(error => next(error))  
  })

  app.delete('/api/persons/:id', (request, response, next) => {
    Person.findByIdAndRemove(request.params.id)
      .then(result => {
        response.status(204).end()
    })
    .catch(error => next(error))
  })

  app.put('/api/persons/:id', (request, response, next) => {
    const body = request.body
    const person = {
      name: body.name,
      number: body.number,
      favourite: body.favourite,
    }
    Person.findByIdAndUpdate(request.params.id, person, { new: true })
      .then(updatedPerson => {
        response.json(updatedPerson)
      })
      .catch(error => next(error))
  })

  const generateId = () => {
    const maxId = Math.max(...persons.map(person => person.id)) 
    return Math.floor(Math.random() * 50) + maxId;
  }

  app.post('/api/persons', (request, response) => {
    const body = request.body
 
    if (!body.name) {
      return response.status(400).json({ 
        error: 'name is missing' 
      })
    }
  }
    const person = new Person ({
      name: body.name,
      number: body.number,
      id: generateId(),
      favourite: body.favourite || false,
    })
    person.save().then(savedPerson => {
      response.json(savedPerson)
    })
  })

  const PORT = process.env.PORT
  app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`)
  })
