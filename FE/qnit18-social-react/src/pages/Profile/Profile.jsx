import { Avatar, Box, Button, Card, Tab, Tabs } from '@mui/material';
import React, { useState } from 'react'
import { useParams } from 'react-router-dom'
import PostCard from '../../components/Post/PostCard';
import UserReelCard from '../../components/Reels/UserReelCard';
import { useSelector } from 'react-redux';
import { store } from '../../Redux/store';
import ProfileModal from './ProfileModal';


const tabs = [
  { value: "post", name: "Post" },
  { value: "reels", name: "Reels" },
  { value: "saved", name: "Saved" },
  { value: "Report", name: "Report" },
]

const posts = [1, 1, 1, 1]
const reels = [1, , 1, 1, 1]
const savedPost = [1, 1, 1, 1]

const Profile = () => {
  const { id } = useParams();
  const [value, setValue] = React.useState('post');
  const {auth} = useSelector(store => store)

  const [open, setOpen] = useState(false);
  const handleOpenModal = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  return (
    <Card className='my-10 w-[70%]'>
      <div className='rounded-md'>
        <div className='h-[15rem]'>
          <img
            className='w-full h-full rounded-t-md'
            src="https://ss-images.saostar.vn/wwebp700/2023/11/12/pc/1699804667691/71z1t83e5u1-k1blkf6fqb2-dk0ypygy0e3.jpeg"
            alt="" />
        </div>
        <div className='px-5 flex justify-between items-start mt-5 h-[5rem]'>
          <Avatar
            className='transform -translate-y-24'
            sx={{ width: "10rem", height: "10rem" }}
            src='https://ss-images.saostar.vn/wwebp700/2023/11/12/pc/1699804667691/71z1t83e5u1-k1blkf6fqb2-dk0ypygy0e3.jpeg' />

          {true ?
            (<Button sx={{ borderRadius: "20px" }} variant='outlined' onClick={handleOpenModal}>Edit Profile</Button>)
            : (
              <Button>Follow</Button>
            )}
        </div>
        <div className='p-5'>
          <div>
            <h1 className='py-1 font-bold text-xl'>{auth.user?.firstName + " " + auth.user?.lastName}</h1>
            <p>@{auth.user?.firstName.toLowerCase() + "_" + auth.user?.lastName.toLowerCase()}</p>
          </div>
          <div className='flex gap-5 items-center py-3'>
            <span>41 post</span>
            <span>35 followers</span>
            <span>5 followings</span>
          </div>

          <div>
            <p>Đây là tiểu sử của Pam nèe</p>
          </div>


        </div>
        <section>
          <Box sx={{ width: '100%', borderBottom: 1, borderColor: "divider" }}>
            <Tabs
              value={value}
              onChange={handleChange}
              aria-label="wrapped label tabs example"
            >
              {tabs.map(item => <Tab value={item.value} label={item.name} wrapped />)}
            </Tabs>
          </Box>
          <div className='flex justify-center'>
            {value === "post" ? (
              <div className='space-y-5 w-[70%] my-10'>
                {posts.map(item => (
                  <div className='border border-slate-100 rounded-md'>
                    <PostCard />
                  </div>
                ))}
              </div>
            ) : value === "reels" ? (
              <div className='w-full flex justify-center'>
                <div className='space-y-5 my-10'>
                  {reels.map(item => (
                    <div className='border-slate-100 rounded-md'>
                      <UserReelCard />
                    </div>
                  ))}
                </div>
              </div>
            ) : value === "post" ? (
              <div className='w-full flex justify-center'>
                <div className='space-y-5 my-10'>
                  {posts.map(item => (
                    <div className='border-slate-100 rounded-md'>
                      <UserReelCard />
                    </div>
                  ))}
                </div>
              </div>
            ) : value === "saved" ? (
              <div className='space-y-5 w-[70%] my-10'>
                {savedPost.map(item => (
                  <div className='border border-slate-100 rounded-md'>
                    <PostCard />
                  </div>
                ))}
              </div>
            ) : (
              <div>
                REPORT
              </div>
            )}
          </div>
        </section>

      </div>
      <section>
        <ProfileModal open={open} handleClose={handleClose}/>
      </section>
    </Card>
  )
}

export default Profile