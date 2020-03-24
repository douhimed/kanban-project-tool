import React, { Component } from "react";
import ProjectItem from "./project/ProjectItem";
import LinkComponent from "./ui/Link";
import { connect } from "react-redux";
import { getProjects } from "../services/ProjectServices";
import PropTypes from "prop-types";
import Alert from "./ui/Alert";

class Dashboard extends Component {
  componentDidMount() {
    this.props.getProjects();
  }

  render() {
    const { projects } = this.props.project;

    let dashboard = null;
    if (projects.length < 1)
      dashboard = (
        <Alert
          message="NOT PROJECTS AVAILABLE AT THIS MOMENT"
          classes="info col col-12"
        />
      );
    else
      dashboard = projects.map(project => (
        <ProjectItem project={project} key={project.id} />
      ));

    return (
      <div className="container">
        <LinkComponent
          classes="btn btn-info"
          to="/projects/new"
          label="New Project"
        />
        <hr />
        <div className="row">{dashboard}</div>
      </div>
    );
  }
}

Dashboard.propTypes = {
  project: PropTypes.object.isRequired,
  getProjects: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  project: state.project
});

export default connect(mapStateToProps, { getProjects })(Dashboard);
