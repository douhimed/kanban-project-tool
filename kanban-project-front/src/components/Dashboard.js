import React, { Component } from "react";
import ProjectItem from "./project/ProjectItem";
import LinkComponent from "./ui/Link";

class Dashboard extends Component {
  state = {};
  render() {
    return (
      <div className="container">
        <LinkComponent
          classes="btn btn-outline-primary"
          to="/projects/new"
          label="New Project"
        />
        <hr />
        <ProjectItem />
      </div>
    );
  }
}

export default Dashboard;
