import React from "react";
import LinkComponent from "../../ui/Link";
import Button from "../../ui/Button";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { deleteTask } from "./../../../services/BacklogServices";

const priorities = ["danger", "warning", "primary"];

const Task = props => {
  const { task } = props;

  const classes = priorities[task.priority - 1];

  return (
    <div className="card mb-3">
      <div className="card-header d-flex justify-content-between">
        <span className="badge badge-info">{task.projectSequence}</span>
        <span className={"badge badge-" + classes}>
          {"Priority : " + task.priority}
        </span>
      </div>
      <div className="card-body">
        <h5 className="card-title">{task.summary}</h5>
        <p className="card-text">{task.acceptanceCriteria}</p>
        <LinkComponent
          classes="btn btn-warning btn-sm m-2"
          to={`/tasks/update/${task.projectIdentifier}/${task.projectSequence}`}
          label="Update"
        />
        <Button
          classes="btn btn-danger btn-sm m-2"
          label="Delete"
          onAction={() =>
            props.deleteTask(task.projectIdentifier, task.projectSequence)
          }
        />
      </div>
    </div>
  );
};

export default connect(null, { deleteTask })(Task);
