import React, { Component } from "react";
import ProjectItem from "./project/ProjectItem";
import LinkComponent from "./ui/Link";
import { connect } from "react-redux";
import { getProjects } from "../services/ProjectServices";
import PropTypes from "prop-types";

class Dashboard extends Component {
  componentDidMount() {
    this.props.getProjects();
  }

  render() {
    const { projects } = this.props.project;

    return (
      <div className="container">
        <LinkComponent
          classes="btn btn-info"
          to="/projects/new"
          label="New Project"
        />
        <hr />
        <div className="row">
          {projects.map(project => (
            <ProjectItem project={project} key={project.id} />
          ))}
        </div>
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
