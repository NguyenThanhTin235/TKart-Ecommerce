import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface User {
  id?: string;
  email: string;
  fullName?: string;
  role?: string;
  avatar?: string;
}

interface AuthState {
  user: User | null;
  token: string | null;
  isAuthenticated: boolean;
  loading: boolean;
  error: string | null;
  otpEmail: string | null; // Used for OTP verification and password recovery flows
}

const initialToken = localStorage.getItem('token');
const initialUser = localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user') as string) : null;

const initialState: AuthState = {
  user: initialUser,
  token: initialToken,
  isAuthenticated: !!initialToken,
  loading: false,
  error: null,
  otpEmail: localStorage.getItem('otpEmail') || null,
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    setLoading: (state, action: PayloadAction<boolean>) => {
      state.loading = action.payload;
    },
    setError: (state, action: PayloadAction<string | null>) => {
      state.error = action.payload;
      state.loading = false;
    },
    setOtpEmail: (state, action: PayloadAction<string | null>) => {
      state.otpEmail = action.payload;
      if (action.payload) {
        localStorage.setItem('otpEmail', action.payload);
      } else {
        localStorage.removeItem('otpEmail');
      }
    },
    loginSuccess: (state, action: PayloadAction<{ token: string; user: User }>) => {
      state.token = action.payload.token;
      state.user = action.payload.user;
      state.isAuthenticated = true;
      state.loading = false;
      state.error = null;
      localStorage.setItem('token', action.payload.token);
      localStorage.setItem('user', JSON.stringify(action.payload.user));
    },
    logout: (state) => {
      state.token = null;
      state.user = null;
      state.isAuthenticated = false;
      state.loading = false;
      state.error = null;
      state.otpEmail = null;
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      localStorage.removeItem('otpEmail');
    },
    updateUser: (state, action: PayloadAction<Partial<User>>) => {
      if (state.user) {
        state.user = { ...state.user, ...action.payload };
        localStorage.setItem('user', JSON.stringify(state.user));
      }
    },
  },
});

export const { setLoading, setError, setOtpEmail, loginSuccess, logout, updateUser } = authSlice.actions;
export default authSlice.reducer;
