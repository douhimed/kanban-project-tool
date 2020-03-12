import React from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import "bootstrap/dist/css/bootstrap.min.css";
import Header from "./components/layouts/header";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import NewProject from "./components/project/NewProject";

class App extends React.Component {
  state = {};
  render() {
    return (
      <Router>
        <Header />
        <Route exact path="/projects" component={Dashboard} />
        <Route exact path="/projects/new" component={NewProject} />
        <Route exact path="/" component={Dashboard} />
      </Router>
    );
  }
}

export default App;
