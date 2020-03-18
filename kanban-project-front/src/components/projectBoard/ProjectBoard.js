import React, { Component } from "react";
import ProjectBoardHead from "../layouts/ProjectBoardHead";
import LinkComponent from "../ui/Link";
import Backlog from "./Backlog";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { getBacklog } from "../../services/BacklogServices";
import Aux from "../HOC/Auxilary";
import Alert from "../ui/Alert";

const alerts = [<Alert classes="danger" />, <Alert classes="infos" />];

class ProjectBoard extends Component {
  state = {
    errors: {},
    hasError: false
  };

  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getBacklog(id);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors)
      this.setState({ errors: nextProps.errors, hasError: true });
  }

  render() {
    const { id } = this.props.match.params;

    return (
      <div className="container">
        <div className="d-flex justify-content-between align-self-baseline">
          <LinkComponent
            classes="btn btn-info"
            to={`/tasks/new/${id}`}
            label="New Task"
          />
          <LinkComponent
            classes="btn btn-warning"
            to={"/projects"}
            label="Back To  Dashboard"
          />
        </div>
        <hr />
        {this.getTheDashboard()}
      </div>
    );
  }

  getTheDashboard() {
    const { errors } = this.state;
    let dashboard = null;
    if (errors.projectNotFounds)
      dashboard = (
        <Alert message={this.props.errors.projectNotFounds} classes="danger" />
      );
    else if (this.props.backlog.tasks.length < 1)
      dashboard = (
        <Alert message="NOT TASKS AVAILABLE FOR THIS PROJECT" classes="info" />
      );
    else
      dashboard = (
        <Aux>
          <ProjectBoardHead />
          <Backlog tasks={this.props.backlog.tasks} />
        </Aux>
      );
    return dashboard;
  }
}

const mapStateToProps = state => ({
  backlog: state.backlog,
  errors: state.errors
});

ProjectBoard.propTypes = {
  backlog: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired,
  getBacklog: PropTypes.func.isRequired
};

export default connect(mapStateToProps, { getBacklog })(ProjectBoard);
