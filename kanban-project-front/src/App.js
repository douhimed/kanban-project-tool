import React from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import "bootstrap/dist/css/bootstrap.min.css";
import Header from "./components/layouts/header";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import NewProject from "./components/project/NewProject";
import { Provider } from "react-redux";
import store from "./store";

class App extends React.Component {
  state = {};
  render() {
    return (
      <Provider store={store}>
        <Router>
          <Header />
          <Route exact path="/projects" component={Dashboard} />
          <Route exact path="/projects/new" component={NewProject} />
          <Route exact path="/" component={Dashboard} />
        </Router>
      </Provider>
    );
  }
}

export default App;
