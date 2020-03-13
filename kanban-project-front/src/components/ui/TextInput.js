import React from "react";

const TextInput = props => {
  const content =
    props.value === "" ? PushSubscriptionOptions.placeholder : props.value;
  return (
    <div class="form-group row">
      <label for="inputEmail3" class="col-sm-2 col-form-label">
        {props.label}
      </label>
      <div class="col-sm-10">
        <textarea
          className="form-control"
          rows="5"
          name={props.name}
          onChange={e => props.onChangeHandler(e)}
        >
          {content}
        </textarea>
      </div>
    </div>
  );
};

export default TextInput;
