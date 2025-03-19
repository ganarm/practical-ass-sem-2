// import React, { Component } from 'react';

// class Form extends Component {
//   constructor(props) {
//     super(props);
//     // Pre-populated state with the given values
//     this.state = {
//       name: 'ganesh mali',
//       email: 'ganarm2003@gmail.com',
//       nameError: '',
//       emailError: ''
//     };
//   }

//   // Validate that the name contains only letters and spaces
//   validateName = (name) => {
//     // Allow only letters and spaces
//     const pattern = /^[A-Za-z\s]*$/;
//     if (!pattern.test(name)) {
//       return 'Name should only contain letters and spaces.';
//     }
//     return '';
//   };

//   // Validate email format
//   validateEmail = (email) => {
//     // Simple email regex
//     const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
//     if (!pattern.test(email)) {
//       return 'Please enter a valid email.';
//     }
//     return '';
//   };

//   // Real-time validation on each keystroke
//   handleChange = (e) => {
//     const { name, value } = e.target;
//     let errorMsg = '';
//     if (name === 'name') {
//       errorMsg = this.validateName(value);
//       this.setState({ name: value, nameError: errorMsg });
//     } else if (name === 'email') {
//       errorMsg = this.validateEmail(value);
//       this.setState({ email: value, emailError: errorMsg });
//     }
//   };

//   // Validate all fields on form submission
//   handleSubmit = (e) => {
//     e.preventDefault();
//     const nameError = this.validateName(this.state.name);
//     const emailError = this.validateEmail(this.state.email);
//     if (nameError || emailError) {
//       this.setState({ nameError, emailError });
//       return;
//     }
//     // If validation passes, proceed with submission logic
//     console.log('Form submitted', this.state);
//   };

//   render() {
//     const { name, email, nameError, emailError } = this.state;
//     return (
//       <div className="container my-5">
//         <div className="card shadow">
//           <div className="card-header bg-info text-white">
//             <h3>Interactive Form</h3>
//           </div>
//           <div className="card-body">
//             <form className="needs-validation" noValidate onSubmit={this.handleSubmit}>
//               <div className="mb-3">
//                 <label className="form-label">Name:</label>
//                 <input
//                   type="text"
//                   name="name"
//                   value={name}
//                   onChange={this.handleChange}
//                   className={`form-control ${nameError ? 'is-invalid' : 'is-valid'}`}
//                   placeholder="Enter your name"
//                   required
//                 />
//                 {nameError && <div className="invalid-feedback">{nameError}</div>}
//               </div>
//               <div className="mb-3">
//                 <label className="form-label">Email:</label>
//                 <input
//                   type="email"
//                   name="email"
//                   value={email}
//                   onChange={this.handleChange}
//                   className={`form-control ${emailError ? 'is-invalid' : 'is-valid'}`}
//                   placeholder="Enter your email"
//                   required
//                 />
//                 {emailError && <div className="invalid-feedback">{emailError}</div>}
//               </div>
//               <button className="btn btn-primary" type="submit">
//                 Submit form
//               </button>
//             </form>
//           </div>
//           <div className="card-footer">
//             <p className="lead">
//               <strong>Entered Name:</strong> <span className="text-primary">{name}</span>
//             </p>
//             <p className="lead">
//               <strong>Entered Email:</strong> <span className="text-primary">{email}</span>
//             </p>
//           </div>
//         </div>
//       </div>
//     );
//   }
// }

// export default Form;



import React, { Component } from 'react';

class Form extends Component {
  constructor(props) {
    super(props);
    this.state = {
      firstName: '',
      middleName: '',
      lastName: '',
      mobile: '',
      email: '',
      gender: '',
      country: '',
      terms: false,
      errors: {
        firstName: '',
        middleName: '',
        lastName: '',
        mobile: '',
        email: '',
        gender: '',
        country: '',
        terms: ''
      }
    };
  }

  // Validation functions
  validateNameField = (value, fieldName) => {
    const pattern = /^[A-Za-z\s]*$/;
    if (!value.trim()) {
      return `${fieldName} is required.`;
    } else if (!pattern.test(value)) {
      return `${fieldName} should only contain letters and spaces.`;
    }
    return '';
  };

  validateMobile = (mobile) => {
    const pattern = /^[0-9]{10}$/;
    if (!mobile.trim()) {
      return 'Mobile number is required.';
    } else if (!pattern.test(mobile)) {
      return 'Mobile number must be exactly 10 digits.';
    }
    return '';
  };

  validateEmail = (email) => {
    const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!email.trim()) {
      return 'Email is required.';
    } else if (!pattern.test(email)) {
      return 'Please enter a valid email.';
    }
    return '';
  };

  validateGender = (gender) => {
    if (!gender) {
      return 'Please select your gender.';
    }
    return '';
  };

  validateCountry = (country) => {
    if (!country) {
      return 'Please select a country.';
    }
    return '';
  };

  validateTerms = (terms) => {
    if (!terms) {
      return 'You must accept the terms and conditions.';
    }
    return '';
  };

  // Real-time validation handler
  handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    const fieldValue = type === 'checkbox' ? checked : value;
    this.setState({ [name]: fieldValue }, () => {
      let error = '';
      switch (name) {
        case 'firstName':
          error = this.validateNameField(this.state.firstName, 'First name');
          break;
        case 'middleName':
          error = this.validateNameField(this.state.middleName, 'Middle name');
          break;
        case 'lastName':
          error = this.validateNameField(this.state.lastName, 'Last name');
          break;
        case 'mobile':
          error = this.validateMobile(this.state.mobile);
          break;
        case 'email':
          error = this.validateEmail(this.state.email);
          break;
        case 'gender':
          error = this.validateGender(this.state.gender);
          break;
        case 'country':
          error = this.validateCountry(this.state.country);
          break;
        case 'terms':
          error = this.validateTerms(this.state.terms);
          break;
        default:
          break;
      }
      this.setState(prevState => ({
        errors: { ...prevState.errors, [name]: error }
      }));
    });
  };

  // On form submission, validate all fields
  handleSubmit = (e) => {
    e.preventDefault();
    const errors = {
      firstName: this.validateNameField(this.state.firstName, 'First name'),
      middleName: this.validateNameField(this.state.middleName, 'Middle name'),
      lastName: this.validateNameField(this.state.lastName, 'Last name'),
      mobile: this.validateMobile(this.state.mobile),
      email: this.validateEmail(this.state.email),
      gender: this.validateGender(this.state.gender),
      country: this.validateCountry(this.state.country),
      terms: this.validateTerms(this.state.terms)
    };

    this.setState({ errors });
    const isValid = Object.values(errors).every(err => err === '');
    if (isValid) {
      console.log('Form submitted successfully with data:', this.state);
      // Process the valid form submission as needed
    }
  };

  render() {
    const { firstName, middleName, lastName, mobile, email, gender, country, terms, errors } = this.state;
    return (
      <div className="container my-5">
        <div className="card shadow">
          <div className="card-header bg-info text-white">
            <h3>Interactive Form</h3>
          </div>
          <div className="card-body">
            <form className="needs-validation" noValidate onSubmit={this.handleSubmit}>
              {/* Row 1: First Name and Middle Name */}
              <div className="row">
                <div className="col-md-6 mb-3">
                  <label className="form-label">First Name</label>
                  <input
                    type="text"
                    name="firstName"
                    value={firstName}
                    onChange={this.handleChange}
                    className={`form-control ${errors.firstName ? 'is-invalid' : firstName && 'is-valid'}`}
                    placeholder="Enter your first name"
                    required
                  />
                  {errors.firstName && <div className="invalid-feedback">{errors.firstName}</div>}
                </div>
                <div className="col-md-6 mb-3">
                  <label className="form-label">Middle Name</label>
                  <input
                    type="text"
                    name="middleName"
                    value={middleName}
                    onChange={this.handleChange}
                    className={`form-control ${errors.middleName ? 'is-invalid' : middleName && 'is-valid'}`}
                    placeholder="Enter your middle name"
                    required
                  />
                  {errors.middleName && <div className="invalid-feedback">{errors.middleName}</div>}
                </div>
              </div>

              {/* Row 2: Last Name and Mobile Number */}
              <div className="row">
                <div className="col-md-6 mb-3">
                  <label className="form-label">Last Name</label>
                  <input
                    type="text"
                    name="lastName"
                    value={lastName}
                    onChange={this.handleChange}
                    className={`form-control ${errors.lastName ? 'is-invalid' : lastName && 'is-valid'}`}
                    placeholder="Enter your last name"
                    required
                  />
                  {errors.lastName && <div className="invalid-feedback">{errors.lastName}</div>}
                </div>
                <div className="col-md-6 mb-3">
                  <label className="form-label">Mobile No</label>
                  <input
                    type="text"
                    name="mobile"
                    value={mobile}
                    onChange={this.handleChange}
                    className={`form-control ${errors.mobile ? 'is-invalid' : mobile && 'is-valid'}`}
                    placeholder="Enter your mobile number"
                    required
                  />
                  {errors.mobile && <div className="invalid-feedback">{errors.mobile}</div>}
                </div>
              </div>

              {/* Row 3: Email and Gender */}
              <div className="row">
                <div className="col-md-6 mb-3">
                  <label className="form-label">Email</label>
                  <input
                    type="email"
                    name="email"
                    value={email}
                    onChange={this.handleChange}
                    className={`form-control ${errors.email ? 'is-invalid' : email && 'is-valid'}`}
                    placeholder="Enter your email"
                    required
                  />
                  {errors.email && <div className="invalid-feedback">{errors.email}</div>}
                </div>
                <div className="col-md-6 mb-3">
                  <label className="form-label">Gender</label>
                  <div>
                    <div className="form-check form-check-inline">
                      <input
                        className="form-check-input"
                        type="radio"
                        name="gender"
                        id="male"
                        value="male"
                        checked={gender === 'male'}
                        onChange={this.handleChange}
                        required
                      />
                      <label className="form-check-label" htmlFor="male">Male</label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input
                        className="form-check-input"
                        type="radio"
                        name="gender"
                        id="female"
                        value="female"
                        checked={gender === 'female'}
                        onChange={this.handleChange}
                        required
                      />
                      <label className="form-check-label" htmlFor="female">Female</label>
                    </div>
                    <div className="form-check form-check-inline">
                      <input
                        className="form-check-input"
                        type="radio"
                        name="gender"
                        id="other"
                        value="other"
                        checked={gender === 'other'}
                        onChange={this.handleChange}
                        required
                      />
                      <label className="form-check-label" htmlFor="other">Other</label>
                    </div>
                    {errors.gender && <div className="text-danger">{errors.gender}</div>}
                  </div>
                </div>
              </div>

              {/* Row 4: Country and Terms */}
              <div className="row">
                <div className="col-md-6 mb-3">
                  <label className="form-label">Country</label>
                  <select
                    className={`form-control ${errors.country ? 'is-invalid' : country && 'is-valid'}`}
                    name="country"
                    value={country}
                    onChange={this.handleChange}
                    required
                  >
                    <option value="">Select Country</option>
                    <option value="USA">USA</option>
                    <option value="Canada">Canada</option>
                    <option value="India">India</option>
                    <option value="Australia">Australia</option>
                  </select>
                  {errors.country && <div className="invalid-feedback">{errors.country}</div>}
                </div>
                <div className="col-md-6 mb-3 d-flex align-items-center">
                  <div className="form-check">
                    <input
                      type="checkbox"
                      className={`form-check-input ${errors.terms ? 'is-invalid' : terms && 'is-valid'}`}
                      name="terms"
                      id="terms"
                      checked={terms}
                      onChange={this.handleChange}
                      required
                    />
                    <label className="form-check-label" htmlFor="terms">
                      Agree to terms and conditions
                    </label>
                    {errors.terms && <div className="invalid-feedback">{errors.terms}</div>}
                  </div>
                </div>
              </div>

              {/* Row 5: Submit Button Centered */}
              <div className="row">
                <div className="col text-center">
                  <button className="btn btn-primary" type="submit">
                    Submit form
                  </button>
                </div>
              </div>
            </form>
          </div>
          <div className="card-footer">
            <h5>Entered Data:</h5>
            <p><strong>First Name:</strong> <span className="text-primary">{firstName}</span></p>
            <p><strong>Middle Name:</strong> <span className="text-primary">{middleName}</span></p>
            <p><strong>Last Name:</strong> <span className="text-primary">{lastName}</span></p>
            <p><strong>Mobile No:</strong> <span className="text-primary">{mobile}</span></p>
            <p><strong>Email:</strong> <span className="text-primary">{email}</span></p>
            <p><strong>Gender:</strong> <span className="text-primary">{gender}</span></p>
            <p><strong>Country:</strong> <span className="text-primary">{country}</span></p>
            <p>
              <strong>Accepted Terms:</strong>{' '}
              <span className="text-primary">{terms ? 'Yes' : 'No'}</span>
            </p>
          </div>
        </div>
      </div>
    );
  }
}

export default Form;

