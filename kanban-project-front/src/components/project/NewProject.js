import React from "react";
import Button from "../ui/Button";
import InputComponent from "../ui/Input";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { createProject } from "./../../services/ProjectServices";

class NewProject extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      projectName: "",
      projectIdentifier: "",
      description: "",
      startDate: "",
      endDate: ""
    };

    this.onInputChangeHandler = this.onInputChangeHandler.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  onInputChangeHandler(event) {
    this.setState({ [event.target.name]: event.target.value });
  }

  onSubmit(event) {
    event.preventDefault();
    const newProject = {
      projectName: this.state.projectName,
      projectIdentifier: this.state.projectIdentifier,
      description: this.state.description,
      startDate: this.state.startDate,
      enddate: this.state.endDate
    };
    this.props.createProject(newProject, this.props.history);
  }

  render() {
    return (
      <div className="container">
        <h1>Create/Edit Project</h1>
        <hr />
        <form onSubmit={this.onSubmit}>
          <InputComponent
            label="Name"
            type="text"
            placeholder="Project Name"
            value={this.state.projectName}
            name="projectName"
            onChangeHandler={this.onInputChangeHandler}
          />
          <InputComponent
            label="Identifier"
            type="text"
            placeholder="Project Identifier"
            value={this.state.projectIdentifier}
            name="projectIdentifier"
            onChangeHandler={this.onInputChangeHandler}
          />
          <InputComponent
            label="Description"
            type="text"
            placeholder="Project Description"
            value={this.state.description}
            name="description"
            onChangeHandler={this.onInputChangeHandler}
          />
          <InputComponent
            label="Start Date"
            type="date"
            placeholder="Start Date"
            value={this.state.startDate}
            name="startDate"
            onChangeHandler={this.onInputChangeHandler}
          />
          <InputComponent
            label="Estimated End Date"
            type="date"
            placeholder="Estimated End Date"
            value={this.state.endDate}
            name="endDate"
            onChangeHandler={this.onInputChangeHandler}
          />
          <Button classes="btn btn-primary" label="Submit" />
        </form>
      </div>
    );
  }
}

NewProject.propTypes = {
  createProject: PropTypes.func.isRequired
};

export default connect(null, { createProject })(NewProject);
