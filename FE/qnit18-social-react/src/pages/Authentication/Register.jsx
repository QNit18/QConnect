
import { Button, FormControlLabel, FormLabel, Radio, RadioGroup, TextField } from '@mui/material'
import { ErrorMessage, Field, Form, Formik } from 'formik'
import React, { useState } from 'react'
import { useDispatch } from 'react-redux'
import * as Yup from "yup"
import { registerUserAction } from '../../Redux/Auth/auth.action'
import { useNavigate } from 'react-router-dom'


const initialValues = { firstName: "", lastName: "", email: "", password: "", gender: "" }
const validationSchema = {
  email: Yup.string().email("Invalid email").required("Email is required"),
  password: Yup.string().min(6, "Password must be at least 6 characters").required("Password is Required")
};

const Register = () => {

  const [gender, setGender] = useState('female');
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handlerSubmit = (values) => {
    values.gender = gender;
    console.log("Handler submit", values)
    dispatch(registerUserAction({data:values}))
  }

  const handleChange = (event) => {
    setGender(event.target.value);
  };

  return (
    <>
      <Formik
        onSubmit={handlerSubmit}
        //validationSchema={validationSchema}
        initialValues={initialValues}
      >
        <Form className='space-y-5'>
          <div className='space-y-5'>

            <div>
              <Field
                as={TextField}
                name="firstName"
                placeholder="Tên"
                type="text"
                variant="outlined"
                fullWidth
              />
              <ErrorMessage name='firstName' component={"div"} className='text-red-500' />
            </div>
            <div>
              <Field
                as={TextField}
                name="lastName"
                placeholder="Họ"
                type="text"
                variant="outlined"
                fullWidth
              />
              <ErrorMessage name='lastName' component={"div"} className='text-red-500' />
            </div>

            <div>
              <Field
                as={TextField}
                name="email"
                placeholder="Email"
                type="email"
                variant="outlined"
                fullWidth
              />
              <ErrorMessage name='email' component={"div"} className='text-red-500' />
            </div>
            <div>
              <Field
                as={TextField}
                name="password"
                placeholder="Password"
                type="password"
                variant="outlined"
                fullWidth
              />
              <ErrorMessage name='password' component="div" className='text-red-500' />
            </div>

            <div>
              <RadioGroup
                onChange={handleChange}
                row
                aria-labelledby="gender"
                name="gender"
              >
                <FormControlLabel value="female" control={<Radio />} label="Female" />
                <FormControlLabel value="male" control={<Radio />} label="Male" />
                <ErrorMessage name='gender' component="div" className='text-red-500' />
              </RadioGroup>
            </div>
          </div>
          <Button
            sx={{ padding: ".8rem 0rem" }}
            fullWidth type="submit"
            variant="contained"
            color="primary">
            Đăng ký
          </Button>
        </Form>
      </Formik>
      <div className='flex gap-3 items-center justify-center pt-5'>
        <p>If you have already account ? </p>
        <Button onClick={() => navigate("/login")}>Login</Button>
      </div>
    </>
  )
}

export default Register