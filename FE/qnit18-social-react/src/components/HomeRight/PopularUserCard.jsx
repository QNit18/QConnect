import { Avatar, Button, CardHeader, IconButton } from '@mui/material'
import { blue, red, yellow } from '@mui/material/colors'
import React from 'react'
import MoreVertIcon from '@mui/icons-material/MoreVert';


const PopularUserCard = () => {
  return (
    <div>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: blue[500] }} aria-label="recipe">
            Q
          </Avatar>
        }
        action={
          <Button size='small'>
            Follow
          </Button>
        }
        title="Code with QNit18"
        subheader="@Codewithqnit18"
      />
    </div>
  )
}

export default PopularUserCard