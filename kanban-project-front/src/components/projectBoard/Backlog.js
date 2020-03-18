import React from "react";
import Task from "./Task/Task";

class Backlog extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }
  render() {
    let tasks_TODO = [];
    let tasks_IN_PROGRESS = [];
    let tasks_DONE = [];

    this.props.tasks.forEach(task => {
      if (task.status === "TO_DO")
        tasks_TODO.push(<Task key={task.id} task={task} />);
      else if (task.status === "IN_PROGRESS")
        tasks_IN_PROGRESS.push(<Task key={task.id} task={task} />);
      else if (task.status === "DONE")
        tasks_DONE.push(<Task key={task.id} task={task} />);
    });

    return (
      <div className="row">
        <div className="col col-4">{tasks_TODO}</div>
        <div className="col col-4">{tasks_IN_PROGRESS}</div>
        <div className="col col-4">{tasks_DONE}</div>
      </div>
    );
  }
}

export default Backlog;
