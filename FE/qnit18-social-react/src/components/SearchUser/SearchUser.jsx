import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';

const SearchUser = () => {
  return (
    <div className='flex'>
      <Box
      component="form"
      sx={{
        '& > :not(style)': { m: 1, width: '44ch' },
      }}
      noValidate
      autoComplete="off"
    >
      <TextField id="outlined-basic" label="Search" variant="outlined" />
    </Box>
    </div>
  )
}

export default SearchUser