import React from "react";
import Button from "../ui/Button";
import InputComponent from "../ui/Input";
import { connect } from "react-redux";
import {
  createProject,
  getProject,
  updateProject
} from "../../services/ProjectServices";
import PropTypes from "prop-types";
import TextInput from "../ui/TextInput";

class NewProject extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      id: null,
      name: "",
      projectIdentifier: "",
      description: "",
      startDate: "",
      endDate: "",
      errors: {},
      isNew: true
    };

    this.onInputChangeHandler = this.onInputChangeHandler.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({ errors: nextProps.errors });
    }

    const {
      id,
      name,
      projectIdentifier,
      description,
      startDate,
      endDate
    } = nextProps.project;

    this.setState({
      id,
      name,
      projectIdentifier,
      description,
      startDate,
      endDate
    });
  }

  componentDidMount() {
    const { id } = this.props.match.params;
    if (id) {
      this.setState({ isNew: false });
      this.props.getProject(id, this.props.history);
    }
  }

  onInputChangeHandler(event) {
    this.setState({ [event.target.name]: event.target.value });
  }

  onSubmit(event) {
    event.preventDefault();
    const project = {
      id: this.state.id,
      name: this.state.name,
      projectIdentifier: this.state.projectIdentifier,
      description: this.state.description,
      startDate: this.state.startDate,
      endDate: this.state.endDate
    };
    if (this.state.isNew) this.props.createProject(project, this.props.history);
    else this.props.updateProject(project, this.props.history);
  }

  render() {
    const { errors } = this.state;
    return (
      <div className="container">
        <h1>Create/Edit Project</h1>
        <hr />
        <form onSubmit={this.onSubmit}>
          <InputComponent
            label="Name"
            type="text"
            placeholder="Project Name"
            value={this.state.name}
            name="name"
            onChangeHandler={this.onInputChangeHandler}
            error={errors.name}
          />
          {this.state.isNew && (
            <InputComponent
              label="Identifier"
              type="text"
              placeholder="Project Identifier"
              value={this.state.projectIdentifier}
              name="projectIdentifier"
              onChangeHandler={this.onInputChangeHandler}
              error={errors.projectIdentifier}
              disabled={this.state.isNew}
            />
          )}

          <TextInput
            label="Description"
            type="text"
            placeholder="Project Description"
            value={this.state.description}
            name="description"
            onChangeHandler={this.onInputChangeHandler}
            error={errors.description}
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

NewProject.protoTypes = {
  createProject: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired,
  getProject: PropTypes.func.isRequired,
  updateProject: PropTypes.func.isRequired,
  project: PropTypes.object.isRequired
};

const mapStateToProps = state => ({
  errors: state.errors,
  project: state.project.project
});

export default connect(mapStateToProps, {
  createProject,
  getProject,
  updateProject
})(NewProject);
