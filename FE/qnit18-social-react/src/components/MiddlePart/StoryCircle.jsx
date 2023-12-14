import { Avatar } from '@mui/material'
import React from 'react'

const StoryCircle = () => {
  return (
    <div>
      <div className='flex flex-col items-center mr-4 cursor-pointer'>
          <Avatar
            sx={{ width: "4rem", height: "4rem" }}
            src='https://ss-images.saostar.vn/wwebp700/2023/11/12/pc/1699804667691/71z1t83e5u1-k1blkf6fqb2-dk0ypygy0e3.jpeg' 
          >
          </Avatar>
          <p>New</p>
        </div>
    </div>
  )
}

export default StoryCircle