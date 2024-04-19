import './App.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Home from './components/Home'
import Navbar from './components/Navbar'
import Login from './components/Login'
import Register from './components/Register'
import Payment from './components/Payment'
import Admin from './components/Admin';
import Dashboard  from './components/Dashboard';

function App() {
  return (
    <Router>
      <Navbar />
        <Routes>
          <Route path="/" exact component={<Navbar/>} >
            <Route index element={<Home />} />
            <Route path="/login" element={<Login/>} />
            <Route path="/register" element={<Register/>} />
            <Route path="/payment" element={<Payment/>} />
            <Route path="/admin" element={<Admin/>} />
            <Route path="/dashboard" element={<Dashboard/>} />
          </Route>
        </Routes>
    </Router>
  )
}
  
export default App;
