---
name: Academic Modernism
colors:
  surface: '#faf8ff'
  surface-dim: '#d2d9f4'
  surface-bright: '#faf8ff'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#f2f3ff'
  surface-container: '#eaedff'
  surface-container-high: '#e2e7ff'
  surface-container-highest: '#dae2fd'
  on-surface: '#131b2e'
  on-surface-variant: '#434655'
  inverse-surface: '#283044'
  inverse-on-surface: '#eef0ff'
  outline: '#737686'
  outline-variant: '#c3c6d7'
  surface-tint: '#0053db'
  primary: '#004ac6'
  on-primary: '#ffffff'
  primary-container: '#2563eb'
  on-primary-container: '#eeefff'
  inverse-primary: '#b4c5ff'
  secondary: '#505f76'
  on-secondary: '#ffffff'
  secondary-container: '#d0e1fb'
  on-secondary-container: '#54647a'
  tertiary: '#515659'
  on-tertiary: '#ffffff'
  tertiary-container: '#696e71'
  on-tertiary-container: '#edf1f5'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#dbe1ff'
  primary-fixed-dim: '#b4c5ff'
  on-primary-fixed: '#00174b'
  on-primary-fixed-variant: '#003ea8'
  secondary-fixed: '#d3e4fe'
  secondary-fixed-dim: '#b7c8e1'
  on-secondary-fixed: '#0b1c30'
  on-secondary-fixed-variant: '#38485d'
  tertiary-fixed: '#dfe3e7'
  tertiary-fixed-dim: '#c3c7cb'
  on-tertiary-fixed: '#171c1f'
  on-tertiary-fixed-variant: '#43474b'
  background: '#faf8ff'
  on-background: '#131b2e'
  surface-variant: '#dae2fd'
typography:
  display:
    fontFamily: Manrope
    fontSize: 48px
    fontWeight: '800'
    lineHeight: '1.2'
    letterSpacing: -0.02em
  headline-lg:
    fontFamily: Manrope
    fontSize: 30px
    fontWeight: '700'
    lineHeight: 40px
    letterSpacing: -0.01em
  headline-lg-mobile:
    fontFamily: Manrope
    fontSize: 24px
    fontWeight: '700'
    lineHeight: 32px
  headline-md:
    fontFamily: Manrope
    fontSize: 20px
    fontWeight: '600'
    lineHeight: 28px
  body-lg:
    fontFamily: Manrope
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  body-sm:
    fontFamily: Manrope
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
  label-caps:
    fontFamily: Manrope
    fontSize: 12px
    fontWeight: '700'
    lineHeight: 16px
    letterSpacing: 0.05em
  label-md:
    fontFamily: Manrope
    fontSize: 12px
    fontWeight: '500'
    lineHeight: 16px
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  unit: 4px
  container-max: 1280px
  gutter: 24px
  margin-desktop: 40px
  margin-mobile: 16px
  stack-sm: 8px
  stack-md: 16px
  stack-lg: 32px
---

## Brand & Style
This design system embodies the principles of Academic Modernism: a synthesis of institutional precision and contemporary digital minimalism. The personality is intellectual, trustworthy, and highly organized, catering to users who value clarity and efficiency.

The visual style utilizes a "Clean Canvas" approach—heavy use of white space to reduce cognitive load, paired with sophisticated soft-UI elements. It avoids the austerity of pure brutalism by incorporating gentle shadows and radius-heavy geometry, resulting in a professional environment that feels premium yet accessible. The emotional response is one of calm focus and structured reliability.

## Colors
The palette is rooted in "Scholar Blue" (#2563EB), a primary hue that signals intelligence and action. This is supported by a spectrum of Slate Grays that provide hierarchical depth without the harshness of pure blacks.

- **Primary:** Use for high-emphasis actions, active states, and brand identifiers.
- **Secondary (Slate):** Reserved for supporting text, icons, and secondary navigational elements.
- **Surface/Background:** The UI relies on a layered white-on-off-white strategy to define content areas. 
- **Functional:** Success, Warning, and Error states should follow standard semantic patterns but utilize the same saturation levels as the primary blue to maintain visual harmony.

## Typography
Manrope was selected for its modern, geometric construction that retains a highly professional and scholarly feel. The typeface offers excellent legibility at small sizes, crucial for data-heavy academic or marketplace interfaces.

- **Hierarchy:** Maintain a clear distinction between section headers (SemiBold/Bold) and instructional text (Regular).
- **Currency (VND):** When displaying Vietnamese Đồng, use the `body-lg` or `headline-md` weights. The currency symbol (₫) should follow the value with a non-breaking space.
- **Spacing:** Tighten letter-spacing slightly for display headers to achieve a more "designed" editorial look.

## Layout & Spacing
The layout follows a disciplined 12-column fixed grid for desktop environments, transitioning to a fluid single-column layout for mobile devices. 

- **Grid:** Use a 24px gutter to ensure content breathes effectively.
- **Rhythm:** All vertical spacing should be multiples of the 4px base unit. 
- **Sidebars:** Navigation sidebars (like the profile menu in the reference) should occupy a fixed 280px width on desktop, while the main content area expands to fill the remaining grid columns.
- **Safe Areas:** Maintain a minimum 40px outer margin on desktop to reinforce the minimalist, "gallery" feel of the interface.

## Elevation & Depth
Elevation is communicated through soft, ambient shadows rather than harsh borders. This design system uses three primary tiers of depth:

1.  **Level 0 (Floor):** Background color (#F8FAFC). No shadow.
2.  **Level 1 (Card/Surface):** The primary content container. Use a subtle shadow: `0px 4px 20px rgba(15, 23, 42, 0.05)`.
3.  **Level 2 (Interactive/Floating):** For dropdowns, tooltips, or active modals. Use a more pronounced shadow: `0px 10px 30px rgba(15, 23, 42, 0.1)`.

Avoid using shadows on buttons or small UI elements; reserve them for larger structural containers to maintain the "Academic" cleanliness.

## Shapes
The shape language is defined by "Softened Geometry." While the layout is structured and grid-based, the corners are rounded to 8px-12px to create a more approachable and modern user experience.

- **Standard Containers:** Use 12px (`rounded-lg`) for primary cards and main content areas.
- **Small Elements:** Use 8px (`rounded-md`) for buttons, input fields, and search bars.
- **Selection Indicators:** Active states in sidebars or menus should use a 6px or 8px radius to fit comfortably within their parent containers.

## Components
- **Buttons:** Primary buttons use a solid Blue (#2563EB) background with white text. Secondary buttons should be ghost-style with a Slate-200 border or a light Slate-50 background. 
- **Input Fields:** Use an 8px radius with a 1px border (#E2E8F0). Focus states should transition the border color to Primary Blue with a subtle 2px outer glow (ring).
- **Cards:** White surfaces with a 12px radius and Level 1 shadow. Ensure internal padding is generous (minimum 24px or 32px) to match the minimalist aesthetic.
- **Chips/Badges:** Use low-saturation background colors (e.g., light blue or light gray) with high-contrast text for "Account Tiers" or status labels.
- **Lists:** Sidebar navigation items should have a subtle hover state (#F1F5F9) and use a vertical 3px "active indicator" on the left or a full-width background fill with an 8px radius for selected items.
- **Icons:** Use thin-stroke or light-weight icons (2px stroke) to maintain the airy, professional feel of the system.