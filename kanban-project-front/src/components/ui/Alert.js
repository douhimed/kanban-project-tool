import React from "react";

const Alert = props => {
  return (
    <div className={"alert alert-" + props.classes} role="alert" align="center">
      {props.message}
    </div>
  );
};

export default Alert;
