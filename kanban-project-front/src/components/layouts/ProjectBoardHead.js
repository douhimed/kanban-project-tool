import React from "react";

const ProjectBoardHead = props => {
  return (
    <div className="row">
      <div
        className="alert alert-primary col-4"
        role="alert"
        style={{ borderRadius: 0, textAlign: "center" }}
      >
        To DO
      </div>
      <div
        className="alert alert-primary col-4"
        role="alert"
        style={{ borderRadius: 0, textAlign: "center" }}
      >
        IN PROGRESS
      </div>
      <div
        className="alert alert-primary col-4"
        role="alert"
        style={{ borderRadius: 0, textAlign: "center" }}
      >
        DONE
      </div>
    </div>
  );
};

export default ProjectBoardHead;
