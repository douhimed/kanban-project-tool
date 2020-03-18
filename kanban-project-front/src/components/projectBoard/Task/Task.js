import React from "react";
import LinkComponent from "../../ui/Link";
import Button from "../../ui/Button";

const priorities = ["danger", "warning", "primary"];

const Task = props => {
  const { task } = props;

  const classes = priorities[task.priority - 1];

  return (
    <div className={"card mb-3"}>
      <div className={"card-header bg-" + classes}>Header</div>
      <div className="card-body">
        <h5 className="card-title">{task.summary}</h5>
        <p className="card-text">{task.acceptanceCriteria}</p>
        <LinkComponent
          classes="btn btn-outline-primary btn-sm m-2"
          to={`/projects/update/${task.id}`}
          label="Update"
        />
        <Button
          classes="btn btn-outline-danger btn-sm m-2"
          label="Delete"
          onAction={id => null}
        />
      </div>
    </div>
  );
};

export default Task;
