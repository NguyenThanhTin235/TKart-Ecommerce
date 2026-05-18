import { configureStore } from '@reduxjs/toolkit'
import { setupListeners } from '@reduxjs/toolkit/query'
import authReducer from './authSlice'
import appReducer from './appSlice'

export const store = configureStore({
  reducer: {
    auth: authReducer,
    app: appReducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(),
})

setupListeners(store.dispatch)

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch
