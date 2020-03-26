import React from "react";
import LinkComponent from "../ui/Link";
import Button from "../ui/Button";
import { connect } from "react-redux";
import { deleteProject } from "../../services/ProjectServices";
import PropTypes from "prop-types";

class ProjectItem extends React.Component {
  onDeleteHandler = identifier => {
    this.props.deleteProject(identifier);
  };

  render() {
    const { project } = this.props;
    return (
      <div className="col-4">
        <div className="card text-center mb-2">
          <div className="card-header bg-warning">
            {project.projectIdentifier}
          </div>
          <div className="card-body">
            <h5 className="card-title">{project.name}</h5>
            <p className="card-text">
              {project.description.substring(0, 100) + " ... "}
            </p>
            <LinkComponent
              classes="btn btn-success btn-sm m-2"
              to={`/projects/${project.projectIdentifier}`}
              label="Project Board"
            />
            <LinkComponent
              classes="btn btn-secondary btn-sm m-2"
              to={`/projects/update/${project.projectIdentifier}`}
              label="Update"
            />
            <Button
              classes="btn btn-danger btn-sm m-2"
              label="Delete"
              onAction={id => this.onDeleteHandler(project.projectIdentifier)}
            />
          </div>
        </div>
      </div>
    );
  }
}

ProjectItem.propTypes = {
  deleteProject: PropTypes.func.isRequired
};

export default connect(null, { deleteProject })(ProjectItem);
