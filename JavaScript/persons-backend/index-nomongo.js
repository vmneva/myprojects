/*
Tämä versio on ilman Mongon käyttöä! Mongon mukana tulleet muutokset tiedostossa index.js
*/
const express = require('express')
var morgan = require('morgan')
const app = express()

app.use(express.json())
/*
Tehtävä 3.8:
  - muutettu osa2 persons.js tiedoston url oikeaan  muotoon
  - muutettiin App.js sekä PersonForm.js tiedostoihin URL:it oikeiksi
  - lisätty proxy frontendiin tiedostoon package.json, sillä baseUrl persons.js muotoa '/api/persons'
*/
const cors = require('cors')
app.use(cors())

/* Tehtävä 3.10: tehty tuotantoversio frontendistä komennolla npm run build ja tämän myötä kopioitu tuotantokoodi 
backendin alle build-kansiona. Lisätään vielä alla olevalla koodinpätkällä middleware 
--> nyt onnistuu hakea osoite http://localhost:3001/index.html ja nähdään frontend
*/
app.use(express.static('build'))

// Tehtävä 3.7: Määritetään morgan logaamaan konsoliin tiny-konfiguraation mukaisesti
app.use(morgan('tiny'))   
morgan.token('host', function(req, res) {
    return req.hostname;
});

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
    res.json(persons)
  })
  
  // Tehtävä 3.2:
  app.get('/info', (req, res) => {
    res.send(
        `<p>Phonebook has info for ${persons.length} people</p>
        <p>${new Date()}</p>`)
  })

  // Tehtävä 3.3 (tähän tehty myös GET-pyyntö request-kansioon):
  app.get('/api/persons/:id', (request, response) => {
    const id = Number(request.params.id)
    const person = persons.find(person => person.id === id)
    if (person) {
      response.json(person)
    } else {
      response.status(404).end()
    }
  })

  // Tehtävä 3.4 (tähän tehty myös DELETE-pyyntö request-kansioon):
  app.delete('/api/persons/:id', (request, response) => {
    const id = Number(request.params.id)
    persons = persons.filter(person => person.id !== id)
    response.status(204).end()
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
    const person = {
      name: body.name,
      number: body.number,
      id: generateId()
    }
    persons = persons.concat(person)
    response.json(person)
  })

  const PORT = 3001
  app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`)
  })