import React from "react";
import Error from "./Error";

const TextInput = props => {
  let classes = "form-control ";
  let error = null;
  if (props.error) {
    error = <Error error={props.error} />;
    classes += "is-invalid";
  }

  return (
    <div className="form-group row">
      <label className="col-sm-4 col-form-label">{props.label}</label>
      <div className="col-sm-8">
        <textarea
          className={classes}
          rows="5"
          name={props.name}
          onChange={e => props.onChangeHandler(e)}
          placeholder={props.placeholder}
          value={props.value}
        />
        {error}
      </div>
    </div>
  );
};

export default TextInput;
