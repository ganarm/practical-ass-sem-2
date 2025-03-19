import React, { Component } from 'react';

class DataFetcher extends Component {
  constructor(props) {
    super(props);
    this.state = {
      data: null,
      loading: true,
      error: null
    };
  }

  componentDidMount() {
    // Example API endpoint using JSONPlaceholder
    fetch('https://jsonplaceholder.typicode.com/posts/1')
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then(data => this.setState({ data, loading: false }))
      .catch(error => this.setState({ error, loading: false }));
  }

  render() {
    const { data, loading, error } = this.state;

    return (
      <div className="container my-5">
        <div className="card shadow">
          <div className="card-header bg-secondary text-white">
            <h3>Fetched Data</h3>
          </div>
          <div className="card-body">
            {loading ? (
              <div>Loading data...</div>
            ) : error ? (
              <div className="text-danger">Error: {error.message}</div>
            ) : (
              <pre>{JSON.stringify(data, null, 2)}</pre>
            )}
          </div>
        </div>
      </div>
    );
  }
}

export default DataFetcher;
