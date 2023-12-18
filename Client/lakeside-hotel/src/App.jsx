import React from 'react'
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import "../node_modules/bootstrap/dist/js/bootstrap.min.js"
// import './App.css'
import { BrowserRouter as Router, Routes , Route} from 'react-router-dom'
import AddRoom from './components/room/AddRoom'
import ExistingRooms from "./components/room/ExistingRooms"
import Home from './components/home/Home.jsx'
import EditRoom from './components/room/EditRoom.jsx'
import NavBar from "./components/layout/NavBar"
import Footer from "./components/layout/Footer"
import RoomListing from './components/room/RoomListing.jsx'
import Admin from './components/admin/Admin.jsx'
import Checkout from './components/bookings/Checkout.jsx'
import BookingSuccess from './components/bookings/BookingSuccess.jsx'

function App() {

  return (
    <>
    <main>
      <Router>
        <NavBar/>
        <Routes>
          <Route path='/' element={<Home/>}/>
          <Route path='/edit-room/:roomId' element={<EditRoom/>}/>
          <Route path='/existing-rooms' element={<ExistingRooms/>}/>
          <Route path='/add-room' element={<AddRoom/>}/>
          <Route path='/book-room/:roomId' element={<Checkout/>}/>
          <Route path="/browse-all-rooms" element={<RoomListing/>}/>
          <Route path="/admin" element={<Admin/>}/>
          <Route path="/admin" element={<Admin/>}/>
          <Route path="/booking-success" element={<BookingSuccess/>}/>
          <Route path="/home" element={<Home/>}/>

        </Routes>
      </Router>
      <Footer/>
    </main>
    </>
  )
}

export default App
