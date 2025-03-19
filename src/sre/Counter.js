import React, { Component } from 'react';

class Counter extends Component {
  constructor(props) {
    super(props);
    this.state = { count: props.initialCount || 0 };
  }

  incrementCount = () => {
    this.setState(prevState => ({ count: prevState.count + 1 }));
  }

  decrementCount = () => {
    this.setState(prevState => ({ count: prevState.count - 1 }));
  }

  render() {
    return (
      <div className="container my-5">
        <div className="card text-center shadow">
          <div className="card-header bg-primary text-white">
            <h3>Counter App</h3>
          </div>
          <div className="card-body">
            <h1 className="display-4">{this.state.count}</h1>
            <div className="btn-group mt-3" role="group">
              <button onClick={this.decrementCount} className="btn btn-danger">Decrement</button>
              <button onClick={this.incrementCount} className="btn btn-success">Increment</button>
            </div>
          </div>
          <div className="card-footer text-muted">
            Simple Counter
          </div>
        </div>
      </div>
    );
  }
}

export default Counter;
