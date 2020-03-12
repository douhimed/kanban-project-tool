import React from "react";

class Header extends React.Component {
  state = {};
  render() {
    return (
      <div className="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
        <h5 className="my-0 mr-md-auto font-weight-normal">
          Kanban Project Management Tool
        </h5>
        <nav className="my-2 my-md-0 mr-md-3">
          <a className="p-2 text-dark" href="#">
            Dashboard
          </a>
        </nav>
        <a className="btn btn-outline-primary" href="#">
          Sign up
        </a>
      </div>
    );
  }
}

export default Header;
