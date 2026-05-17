import { useState } from 'react'
import { LucideShoppingBag } from 'lucide-react'

function App() {
  return (
    <div className="min-h-screen bg-soft-grey flex flex-col items-center justify-center p-4">
      <div className="neo-brutal-card max-w-md w-full text-center">
        <div className="flex justify-center mb-4">
          <div className="bg-accent-red p-4 border-2 border-black shadow-brutal">
            <LucideShoppingBag size={48} className="text-white" />
          </div>
        </div>
        <h1 className="text-4xl font-black mb-4 uppercase tracking-tighter">
          TKart Platform
        </h1>
        <p className="text-lg font-bold mb-6">
          Multi-vendor Marketplace Engine initialized.
        </p>
        <div className="space-y-4">
          <button className="neo-brutal-button w-full uppercase tracking-widest">
            Enter Dashboard
          </button>
          <div className="text-sm font-mono bg-white border border-black p-2">
            Status: Phase 0 Completed
          </div>
        </div>
      </div>

      <footer className="mt-8 text-sm font-black uppercase tracking-widest">
        Powered by Antigravity AI
      </footer>
    </div>
  )
}

export default App
