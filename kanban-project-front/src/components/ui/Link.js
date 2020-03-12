import React from "react";
import { Link } from "react-router-dom";

const LinkComponent = props => {
  return (
    <Link className={props.classes} to={props.to} {...props}>
      {props.label}
    </Link>
  );
};

export default LinkComponent;
