import React, { Component } from "react";
import ProjectBoardHead from "../layouts/ProjectBoardHead";
import LinkComponent from "../ui/Link";
import Backlog from "./Backlog";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import { getBacklog } from "../../services/BacklogServices";

class ProjectBoard extends Component {
  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getBacklog(id);
  }

  render() {
    const { id } = this.props.match.params;

    return (
      <div className="container">
        <div className="d-flex justify-content-between align-self-baseline">
          <LinkComponent
            classes="btn btn-info"
            to={`/tasks/new/${id}`}
            label="New Task"
          />
          <LinkComponent
            classes="btn btn-warning"
            to={"/projects"}
            label="Back To  Dashboard"
          />
        </div>
        <hr />
        <ProjectBoardHead />
        <Backlog tasks={this.props.backlog.tasks} />
      </div>
    );
  }
}

const mapStateToProps = state => ({
  backlog: state.backlog
});

ProjectBoard.propTypes = {
  backlog: PropTypes.object.isRequired,
  getBacklog: PropTypes.func.isRequired
};

export default connect(mapStateToProps, { getBacklog })(ProjectBoard);
