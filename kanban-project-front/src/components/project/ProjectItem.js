import React from "react";
import LinkComponent from "../ui/Link";
import Button from "../ui/Button";

class ProjectItem extends React.Component {
  state = {};
  render() {
    return (
      <React.Fragment>
        <div className="row">
          <div className="col-4">
            <div className="card text-center mb-2">
              <div className="card-header">Featured</div>
              <div className="card-body">
                <h5 className="card-title">Special title treatment</h5>
                <p className="card-text">
                  With supporting text below as a natural lead-in to additional
                  content.
                </p>
                <LinkComponent
                  classes="btn btn-success btn-sm m-2"
                  to="#"
                  label="Details"
                />
                <LinkComponent
                  classes="btn btn-info btn-sm m-2"
                  to="#"
                  label="Details"
                />
                <Button classes="btn btn-danger btn-sm m-2" label="Delete" />
              </div>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default ProjectItem;
