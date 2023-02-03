/*
Yksinkertainen sovellus palautteen keräämiseen ja statistiikkaan.
*/

import { useState } from 'react'

const Button = ({ handleClick, text }) => (
  <button onClick={handleClick}>
    {text}
  </button>
)

const StatisticLine = (props) => {
  return (
    <div>{props.text} {props.value}</div>
  )
}

const Statistics = (props) => {
  let sum = 0
  const giveAverage = () => {
    props.all.forEach(value => {
      sum += value
    })
    return sum/props.all.length 
  }

  const givePositives = () => {
    const posArr = props.all.filter(num => num > 0);
    return 100*posArr.length/props.all.length + ' %' 
  }

  if (props.all.length === 0) {
    return (
      <div>
        <h1>Statistics:</h1>
        No feedback given
      </div>
    )
  }

  return (
    <div>
      <h1>Statistics:</h1>
      <StatisticLine text="good" value={props.good} />
      <StatisticLine text="neutral" value={props.neutral} />
      <StatisticLine text="bad" value={props.bad} />
      <StatisticLine text="all" value={props.all.length} />
      <StatisticLine text="average" value={giveAverage(props.all)} />
      <StatisticLine text="positive" value={givePositives(props.all)} />
    </div>
  )
}

const App = () => {
  // tallenna napit omaan tilaansa
  const [good, setGood] = useState(0)
  const [neutral, setNeutral] = useState(0)
  const [bad, setBad] = useState(0)
  const [all, setAll] = useState([])

  const giveGood = () => {
    all.push(1)
    setAll(all)
    setGood(good + 1)
  }

  const giveNeutral = () => {
    all.push(0)
    setAll(all)
    setNeutral(neutral + 1)
  }

  const giveBad = () => {
    all.push(-1)
    setAll(all)
    setBad(bad + 1)
  }

  return (
    <div>
      <h1>Give feedback!</h1>
      <Button handleClick={giveGood} text='good' />
      <Button handleClick={giveNeutral} text='neutral' />
      <Button handleClick={giveBad} text='bad' />
      <Statistics good={good} neutral={neutral} bad={bad} all={all} />
    </div>
  )
}

export default App

