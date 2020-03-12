import React from "react";

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
                <a href="#" className="btn btn-success btn-sm m-2">
                  Infos
                </a>
                <a href="#" className="btn btn-info btn-sm m-2">
                  Update
                </a>
                <a href="#" className="btn btn-danger btn-sm m-2">
                  Delete
                </a>
              </div>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default ProjectItem;
