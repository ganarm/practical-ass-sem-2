import React from 'react';
import HelloWorld from './sre/HelloWorld';
import Counter from './sre/Counter';
import Form from './sre/Form';
import DataFetcher from './sre/DataFetcher';
import CustomProgressBar from './sre/CustomProgressBar';

function App() {
  return (
    <div style={{ padding: '20px' }}>
      <section style={{ marginBottom: '40px' }}>
        <h1>Qno 1: Hello World Component</h1>
        <HelloWorld />
      </section>

      <section style={{ marginBottom: '40px' }}>
        <h1>Qno 2: Counter Component</h1>
        <Counter initialCount={0} />
      </section>

      <section style={{ marginBottom: '40px' }}>
        <h1>Qno 3: Form Component</h1>
        <Form />
      </section>

      <section style={{ marginBottom: '40px' }}>
        <h1>Qno 4: DataFetcher Component</h1>
        <DataFetcher />
      </section>

      <section style={{ marginBottom: '40px' }}>
        <h1>Qno 5: Custom Progress Bar</h1>
        <CustomProgressBar progress={50} />
      </section>
    </div>
  );
}

export default App;
