import React from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import "bootstrap/dist/css/bootstrap.min.css";
import Header from "./components/layouts/header";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import NewProject from "./components/project/NewProject";
import { Provider } from "react-redux";
import store from "./store";
import ProjectBoard from "./components/projectBoard/ProjectBoard";
import TaskForm from "./components/projectBoard/Task/TaskForm";

class App extends React.Component {
  state = {};
  render() {
    return (
      <Provider store={store}>
        <Router>
          <Header />
          <Switch>
            <Route exact path="/tasks/new/:id" component={TaskForm} />
            <Route
              exact
              path="/tasks/update/:id/:taskId"
              component={TaskForm}
            />
            <Route exact path="/projects/update/:id" component={NewProject} />
            <Route exact path="/projects/new" component={NewProject} />
            <Route exact path="/projects/:id" component={ProjectBoard} />
            <Route exact path="/projects" component={Dashboard} />
            <Route exact path="/" component={Dashboard} />
          </Switch>
        </Router>
      </Provider>
    );
  }
}

export default App;
