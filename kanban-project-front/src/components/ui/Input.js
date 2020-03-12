import React from "react";

const InputComponent = props => {
  return (
    <div className="form-group row">
      <label className="col-sm-2 col-form-label">{props.label}</label>
      <div className="col-sm-6">
        <input
          type={props.type}
          className="form-control"
          placeholder={props.placeholder}
          name={props.name}
          value={props.value}
          onChange={e => props.onChangeHandler(e)}
        />
      </div>
    </div>
  );
};

export default InputComponent;
