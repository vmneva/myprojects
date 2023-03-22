require('dotenv').config()  //otetaan käyttöön dotenv-kirjasto ympäristömuuttujan arvon määrittelyyn
const express = require('express')
var morgan = require('morgan')
const Person = require('./models/person')
const app = express()
const cors = require('cors')
/*
Tehtävä 3.8:
  - muutettu osa2 persons.js tiedoston url oikeaan  muotoon
  - muutettiin App.js sekä PersonForm.js tiedostoihin URL:it oikeiksi
  - lisätty proxy frontendiin tiedostoon package.json, sillä baseUrl persons.js muotoa '/api/persons'
*/
app.use(cors())
/* Tehtävä 3.10: tehty tuotantoversio frontendistä komennolla npm run build ja tämän myötä kopioitu tuotantokoodi 
backendin alle build-kansiona. Lisätään vielä alla olevalla koodinpätkällä middleware 
--> nyt onnistuu hakea osoite http://localhost:3001/index.html ja nähdään frontend
*/
app.use(express.json())
app.use(express.static('build'))
// Tehtävä 3.7: Määritetään morgan logaamaan konsoliin tiny-konfiguraation mukaisesti
app.use(morgan('tiny'))   
morgan.token('host', function(req, res) {
    return req.hostname;
});
// Tehtävä 3.15: Virheidenkäsittelyn siirto middlewareen
const errorHandler = (error, request, response, next) => {
  console.error(error.message)
//tarkastaa onko kyse CastError-poikkeuksesta eli virheellisestä olio-id:stä
  if (error.name === 'CastError') {
    return response.status(400).send({ error: 'malformatted id' })
  }
  next(error)
}
app.use(errorHandler) //otetaan virheenkäsittelijä käyttöön

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
  
  // Tehtävä 3.12
  app.get('/api/persons', (req, res) => {
    Person.find(req.params).then(persons => { //req.params avulla saadaan tietokannasta puhelinluettelo frontendiin
      res.json(persons)
    })
  })
  
 // Tehtävä 3.2:
  app.get('/info', (req, res) => {
    res.send(
        `<p>Phonebook has info for ${persons.length} people</p>
        <p>${new Date()}</p>`)
  })
  
  // Tehtävä 3.3 & 3.15 (tähän tehty myös GET-pyyntö request-kansioon):
  app.get('/api/persons/:id', (request, response, next) => {
    Person.findById(request.params.id)
    .then(person => {
      if (person) {
        response.json(person)
      } 
      else {  //jos kannasta ei löydy haettua oliota
        response.status(404).end()
      }
    })
    .catch(error => next(error))  //siirretään virhe eteenpäin funktiolle next parametrina
  })

  // Tehtävä 3.4 & 3.14 (tähän tehty myös DELETE-pyyntö request-kansioon):
  app.delete('/api/persons/:id', (request, response, next) => {
    Person.findByIdAndRemove(request.params.id)
      .then(result => {
        response.status(204).end()
    })
    .catch(error => next(error))
  })

  // Tehtävä 3.14: tämän avulla myös favourite-tiedon muutos välittyy tietokantaan
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

  //Tehtävä 3.5-6 (tähän tehty myös POST-pyyntö request-kansioon):
  const generateId = () => {
    const maxId = Math.max(...persons.map(person => person.id))  // Generoidaan ID väliltä sen hetken listan maxID...50:
    return Math.floor(Math.random() * 50) + maxId;
  }

  app.post('/api/persons', (request, response) => {
    const body = request.body
    // Tarkistus nimen puuttumiselle:
    if (!body.name) {
      return response.status(400).json({ 
        error: 'name is missing' 
      })
    }
    // Tarkistus löytyykö nimi jo puhelinluettelosta (TOIMII VAIN ILMAN MONGOA (teht.3.6!):
    else if (persons.find(({name}) => name === body.name)) {
      return response.status(400).json({ 
        error: 'name must be unique' 
      })
  }
    //muokattu metodi niin, että tallennus tapahtuu myös tietokantaan
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

  //muutettiin muuttujan PORT määrittely dotenv-kirjaston mukaan
  const PORT = process.env.PORT
  app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`)
  })