// Tehtävä 3.11
const mongoose = require('mongoose')

//jos komentoriviparametrejä alle kolme, pyydetään salasanaa
if (process.argv.length<3) {
  console.log('give password!')
  process.exit(1)
}

const password = process.argv[2]
const url =
  `mongodb+srv://part3:${password}@cluster0.2mcuqe5.mongodb.net/personApp?retryWrites=true&w=majority`

mongoose.connect(url)

const personSchema = new mongoose.Schema({  //muuttujaan personSchema määritellään henkilön skeema eli miten oliot tulee tallettaa tietokantaan
  name: String,
  number: String,
  favourite: Boolean,
})

const Person = mongoose.model('Person', personSchema)   //modelin parametri Person -> oliot tallettuu kokoelmaan people
//sovellus luo henkilöä vastaavan model:in avulla person-olion
const person = new Person({
  //määritelty komentoriviparametrien avulla:
  name: process.argv[3],
  number: process.argv[4],
  favourite: process.argv[5],
})

//jos komentoriviparametrejä alle 4 (vain salasana), tulostetaan tietokannassa olevat henkilöt
if (process.argv.length<4) {
    console.log(`phonebook:`)
    Person.find({}).then(result => {
        result.forEach(person => {
          console.log(`${person.name} ${person.number}`)
        })
        mongoose.connection.close()
      })
  }
else {
//talletetaan olio tietokantaan ja palautuvalle promiselle rekisteröidään then-metodin avulla tapahtumankäsittelijä
    person.save().then(result => {
        console.log(`added ${person.name} number ${person.number} to phonebook, favourite: ${person.favourite}`)
        mongoose.connection.close()   //kun olio talletettu, suljetaan tietokantayhteys, jotta ohjelman suoritus päättyy
    })
}
