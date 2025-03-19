import React, { useState, useRef } from "react";

const CustomProgressBar = () => {
  const [progress, setProgress] = useState(50); // Initial progress at 50%
  const progressBarRef = useRef(null);

  const handleBarClick = (event) => {
    if (!progressBarRef.current) return;

    const bar = progressBarRef.current;
    const clickPosition = event.clientX - bar.getBoundingClientRect().left;
    const newProgress = (clickPosition / bar.clientWidth) * 100;

    setProgress(Math.min(100, Math.max(0, newProgress))); // Ensure between 0-100
  };

  return (
    <div className="container my-4">
      <div className="card shadow">
        <div className="card-header bg-success text-white">
          <h3>Custom Progress Bar</h3>
        </div>
        <div className="card-body text-center">
          <div
            className="progress"
            ref={progressBarRef}
            style={{ height: "25px", cursor: "pointer" }}
            onClick={handleBarClick} // Click to update progress
          >
            <div
              className="progress-bar progress-bar-striped progress-bar-animated bg-info"
              role="progressbar"
              style={{ width: `${progress}%` }}
            >
              {Math.round(progress)}%
            </div>
          </div>
        </div>
        <div className="card-footer text-muted text-center">
          Click on the progress bar to update progress.
        </div>
      </div>
    </div>
  );
};

export default CustomProgressBar;
