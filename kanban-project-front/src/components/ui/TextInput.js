import React from "react";
import Error from "./Error";

const TextInput = props => {
  let classes = "form-control ";
  let error = null;
  if (props.error) {
    error = <Error error={props.error} />;
    classes += "is-invalid";
  }

  const content =
    props.value === "" ? PushSubscriptionOptions.placeholder : props.value;
  return (
    <div className="form-group row">
      <label className="col-sm-2 col-form-label">{props.label}</label>
      <div className="col-sm-10">
        <textarea
          className={classes}
          rows="5"
          name={props.name}
          onChange={e => props.onChangeHandler(e)}
        >
          {content}
        </textarea>
        {error}
      </div>
    </div>
  );
};

export default TextInput;
