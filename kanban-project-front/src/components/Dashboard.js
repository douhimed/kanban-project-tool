import React, { Component } from "react";
import ProjectItem from "./project/ProjectItem";
import Header from "./layouts/header";

class Dashboard extends Component {
  state = {};
  render() {
    return (
      <div>
        <Header />
        <div className="container">
          <button className="btn btn-outline-primary">New Project</button>
          <hr />
          <ProjectItem />
        </div>
      </div>
    );
  }
}

export default Dashboard;
