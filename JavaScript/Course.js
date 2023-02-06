const Part = (props) => {
    return (
      <div>
        <p>{props.part.name} - {props.part.exercises} exercises</p> 
      </div>
    )
  }
  
  const Header = (props) => {
    return (
      <div>
        <h2>{props.course}</h2>
      </div>
    )
  }
  
  const Content = (props) => {
    return (
      <div>
        {props.parts.map(part => 
            <Part key={part.id} part={part} />
          )}
      </div>
    )
  }
  
  const Total = (props) => {
      let exercises = props.parts.map(part => part.exercises);
      let total = exercises.reduce( (a, b) => a + b ) 
      
      return (
        <div>
          <b>
            total of {total} exercises
          </b>
        </div>
      )
    }
  
  const Course = ({course}) => {
    return (
      <div>
        <Header course={course.name} />
        <Content parts={course.parts} />
        <Total parts={course.parts} />
      </div>
    )
  }
  
export default Course