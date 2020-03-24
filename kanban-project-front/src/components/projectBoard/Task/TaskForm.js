import React, { Component } from "react";
import InputComponent from "../../ui/Input";
import Button from "../../ui/Button";
import TextInput from "../../ui/TextInput";
import { connect } from "react-redux";
import {
  addProjectTask,
  getTask,
  updateProjectTask
} from "../../../services/BacklogServices";
import PropTypes from "prop-types";
import Select from "../../ui/Select";
import LinkComponent from "../../ui/Link";

const options = [
  { name: "Select Priority", value: 0 },
  { name: "Hight", value: 1 },
  { name: "Medium", value: 2 },
  { name: "Low", value: 3 }
];

const status = [
  { name: "Select Status", value: "" },
  { name: "TO DO", value: "TO_DO" },
  { name: "IN PROGRESS", value: "IN_PROGRESS" },
  { name: "DONE", value: "DONE" }
];

class TaskForm extends Component {
  constructor(props) {
    super(props);

    const { id } = this.props.match.params;

    this.state = {
      id: null,
      projectSequence: null,
      summary: "",
      acceptanceCriteria: "",
      status: "",
      priority: "",
      dueDate: null,
      projectIdentifier: id,
      createdAt: null,
      errors: {},
      isNew: true
    };
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }

    const {
      id,
      summary,
      acceptanceCriteria,
      status,
      priority,
      dueDate,
      createdAt,
      projectSequence,
      projectIdentifier
    } = nextProps.task;

    this.setState({
      id,
      summary,
      acceptanceCriteria,
      status,
      priority,
      dueDate,
      createdAt,
      projectSequence,
      projectIdentifier
    });
  }

  componentDidMount() {
    const { taskId } = this.props.match.params;
    if (taskId) {
      this.setState({ isNew: false });
      this.props.getTask(
        this.state.projectIdentifier,
        taskId,
        this.props.history
      );
    }
  }

  onInputChangeHandler = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  onSubmit = event => {
    event.preventDefault();
    const task = {
      summary: this.state.summary,
      acceptanceCriteria: this.state.acceptanceCriteria,
      status: this.state.status,
      priority: this.state.priority,
      dueDate: this.state.dueDate,
      id: this.state.id,
      createdAt: this.state.createdAt,
      projectSequence: this.state.projectSequence,
      projectIdentifier: this.state.projectIdentifier
    };
    if (this.state.isNew)
      this.props.addProjectTask(
        this.state.projectIdentifier,
        task,
        this.props.history
      );
    else
      this.props.updateProjectTask(
        this.state.projectIdentifier,
        task,
        this.props.history
      );
  };

  render() {
    const { errors } = this.state;

    return (
      <div className="container">
        <div className="d-flex justify-content-between align-self-baseline">
          <h3>Create/Edit Task</h3>
          <LinkComponent
            classes="btn btn-warning"
            to={`/projects/${this.state.projectIdentifier}`}
            label="Back To  Backlog"
          />
        </div>
        <hr />
        <form onSubmit={this.onSubmit}>
          <InputComponent
            label="Summary"
            type="text"
            placeholder="Task summary"
            name="summary"
            value={this.state.summary}
            onChangeHandler={this.onInputChangeHandler}
            error={errors.summary}
          />
          <TextInput
            label="Acceptance criteria"
            type="text"
            placeholder="Acceptance criteria"
            name="acceptanceCriteria"
            value={this.state.acceptanceCriteria}
            onChangeHandler={this.onInputChangeHandler}
          />
          <InputComponent
            label="Due date"
            type="date"
            placeholder="Due date"
            name="dueDate"
            value={this.state.dueDate}
            onChangeHandler={this.onInputChangeHandler}
          />
          <Select
            label="Priority"
            name="priority"
            options={options}
            value={this.state.priority}
            onChangeHandler={this.onInputChangeHandler}
          />
          <Select
            label="Status"
            name="status"
            options={status}
            value={this.state.status}
            onChangeHandler={this.onInputChangeHandler}
          />

          <Button classes="btn btn-info" label="Submit" />
        </form>
      </div>
    );
  }
}

TaskForm.propTypes = {
  addProjectTask: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
  getTask: PropTypes.func.isRequired,
  updateProjectTask: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  errors: state.errors,
  task: state.backlog.task
});

export default connect(mapStateToProps, {
  addProjectTask,
  getTask,
  updateProjectTask
})(TaskForm);
