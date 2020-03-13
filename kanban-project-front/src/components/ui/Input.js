import React from "react";
import Error from "./Error";

const InputComponent = props => {
  let classes = "form-control ";
  let error = null;
  if (props.error) {
    error = <Error error={props.error} />;
    classes += "is-invalid";
  }

  return (
    <div className="form-group row">
      <label className="col-sm-2 col-form-label">{props.label}</label>
      <div className="col-sm-6">
        <input
          type={props.type}
          className={classes}
          placeholder={props.placeholder}
          name={props.name}
          value={props.value}
          onChange={e => props.onChangeHandler(e)}
        />
        {error}
      </div>
    </div>
  );
};

export default InputComponent;
