import React from "react";

const Button = props => {
  return (
    <button className={props.classes} onClick={props.onAction}>
      {props.label}
    </button>
  );
};

export default Button;
