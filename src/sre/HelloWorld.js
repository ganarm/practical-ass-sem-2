import React from 'react';

const HelloWorld = () => {
  return (
    <div className="container my-5">
      <div className="card text-center shadow">
        <div className="card-header bg-secondary text-white">
          <h3>Qno 1: Hello World Component</h3>
        </div>
        <div className="card-body">
          <h2 className="display-4">Hello, World!</h2>
        </div>
        <div className="card-footer text-muted">
          Basic React Component
        </div>
      </div>
    </div>
  );
};

export default HelloWorld;
