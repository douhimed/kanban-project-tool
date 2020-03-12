import React from "react";

const Button = props => {
  return (
    <button className={props.classes} {...props} onClick={props.clickHandler}>
      {props.label}
    </button>
  );
};

export default Button;
