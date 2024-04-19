import './App.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Home from './components/Home'
import Navbar from './components/Navbar'
import Login from './components/Login'
import Register from './components/Register'
import Payment from './components/Payment'
import Admin from './components/Admin';
import Dashboard  from './components/Dashboard';
import UserDetails from './components/UserDetails';
import PlansExpiring from './components/PlansExpiring';
import AllPlans from './components/AllPlans';
import PlanDetails from './components/PlanDetails';
import PlanCategory from './components/PlanCategory';
import PaymentSummary from './components/PaymentSummary';

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
            <Route path="/users" element={<UserDetails/>} />
            <Route path="/expiring-plans" element={<PlansExpiring/>} />
            <Route path="/plans" element={<AllPlans/>} />
            <Route path="/plans/:planId" element={<PlanDetails/>} />
            <Route path="/plans-category" element={<PlanCategory/>} />
            <Route path="/payment-summary" element={<PaymentSummary/>} />
          </Route>
        </Routes>
    </Router>
  )
}
  
export default App;
