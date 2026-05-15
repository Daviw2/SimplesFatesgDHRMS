---
name: Enterprise HR Design System
colors:
  surface: '#f7f9fb'
  surface-dim: '#d8dadc'
  surface-bright: '#f7f9fb'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#f2f4f6'
  surface-container: '#eceef0'
  surface-container-high: '#e6e8ea'
  surface-container-highest: '#e0e3e5'
  on-surface: '#191c1e'
  on-surface-variant: '#45464d'
  inverse-surface: '#2d3133'
  inverse-on-surface: '#eff1f3'
  outline: '#76777d'
  outline-variant: '#c6c6cd'
  surface-tint: '#565e74'
  primary: '#000000'
  on-primary: '#ffffff'
  primary-container: '#131b2e'
  on-primary-container: '#7c839b'
  inverse-primary: '#bec6e0'
  secondary: '#515f74'
  on-secondary: '#ffffff'
  secondary-container: '#d5e3fd'
  on-secondary-container: '#57657b'
  tertiary: '#000000'
  on-tertiary: '#ffffff'
  tertiary-container: '#002113'
  on-tertiary-container: '#009668'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#dae2fd'
  primary-fixed-dim: '#bec6e0'
  on-primary-fixed: '#131b2e'
  on-primary-fixed-variant: '#3f465c'
  secondary-fixed: '#d5e3fd'
  secondary-fixed-dim: '#b9c7e0'
  on-secondary-fixed: '#0d1c2f'
  on-secondary-fixed-variant: '#3a485c'
  tertiary-fixed: '#6ffbbe'
  tertiary-fixed-dim: '#4edea3'
  on-tertiary-fixed: '#002113'
  on-tertiary-fixed-variant: '#005236'
  background: '#f7f9fb'
  on-background: '#191c1e'
  surface-variant: '#e0e3e5'
typography:
  display-lg:
    fontFamily: Inter
    fontSize: 48px
    fontWeight: '700'
    lineHeight: 56px
    letterSpacing: -0.02em
  headline-lg:
    fontFamily: Inter
    fontSize: 32px
    fontWeight: '600'
    lineHeight: 40px
    letterSpacing: -0.01em
  headline-md:
    fontFamily: Inter
    fontSize: 24px
    fontWeight: '600'
    lineHeight: 32px
  headline-sm:
    fontFamily: Inter
    fontSize: 20px
    fontWeight: '600'
    lineHeight: 28px
  body-lg:
    fontFamily: Inter
    fontSize: 18px
    fontWeight: '400'
    lineHeight: 28px
  body-md:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  body-sm:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
  label-md:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '500'
    lineHeight: 20px
    letterSpacing: 0.01em
  label-sm:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '600'
    lineHeight: 16px
rounded:
  sm: 0.125rem
  DEFAULT: 0.25rem
  md: 0.375rem
  lg: 0.5rem
  xl: 0.75rem
  full: 9999px
spacing:
  unit: 4px
  container-max: 1440px
  gutter: 24px
  margin-desktop: 40px
  margin-tablet: 24px
  margin-mobile: 16px
---

## Brand & Style
This design system is engineered for high-density enterprise environments where clarity, speed of cognition, and trust are paramount. The visual direction follows a **Corporate Modern** aesthetic—prioritizing functional alignment and rigorous organization over decorative flair. 

The target audience consists of HR professionals and department heads who require a dependable tool for managing complex workforce data. The UI evokes a sense of "quiet authority," utilizing a structured grid and a sophisticated palette to reduce cognitive load during long-duration tasks. Every element is designed to feel intentional and stable, reinforcing the platform's role as a single source of truth for organizational health.

## Colors
The palette is anchored by deep corporate blues and professional slate grays to establish a trustworthy foundation.

*   **Primary (Deep Navy):** Used for navigation, high-level headers, and core branding elements to ground the interface.
*   **Secondary (Slate Gray):** Employed for secondary actions, iconography, and supporting text to provide hierarchy without competition.
*   **Accent (Emerald Green):** A vibrant, high-contrast green used sparingly for primary call-to-actions, success states, and positive growth trends in data visualizations.
*   **Surface & Neutrals:** A range of cool grays (Slate 50-200) defines the workspace, using subtle shifts in value to separate content areas without the need for heavy lines.
*   **Status Colors:** Standardized semantic colors for Error (Rose 600), Warning (Amber 500), and Info (Blue 600) follow accessibility standards for clear communication.

## Typography
Inter is the exclusive typeface for this design system, chosen for its exceptional legibility in data-heavy contexts and its neutral, professional character. 

Hierarchy is established through weight and color rather than excessive size variations. **Label-SM** is utilized for table headers and small captions, often in a slightly muted slate color. **Headline-MD** is the standard for card titles and section headers. For mobile displays, any font size above 32px scales down by a factor of 0.8x to ensure layout integrity. Tighten letter-spacing on larger headings to maintain a modern, "compact" feel.

## Layout & Spacing
The layout follows a **Fluid Grid** model with a 12-column structure for desktop. To maintain the professional and organized feel, this design system utilizes a strict 4px baseline grid. 

*   **Desktop (1280px+):** 40px external margins with 24px gutters. Dashboard cards typically span 3, 4, or 6 columns.
*   **Tablet (768px - 1279px):** 24px margins. Layouts reflow to an 8-column grid. Sidebars should collapse into a hamburger menu or icon-only rail.
*   **Mobile (<767px):** 16px margins on a 4-column grid. Data tables transition into card-based list views to maintain readability.

Whitespace is used aggressively to group related information and prevent the interface from feeling "cramped" despite the high density of enterprise data.

## Elevation & Depth
Depth in this design system is achieved through **Tonal Layering** supplemented by **Ambient Shadows**. 

1.  **Level 0 (Background):** Slate 50 or White.
2.  **Level 1 (Cards/Tables):** White surface with a 1px border in Slate 200. No shadow or an extremely subtle 2px blur shadow (Alpha 0.05).
3.  **Level 2 (Dropdowns/Modals):** White surface with a more pronounced shadow (8px - 16px blur, Alpha 0.1) to clearly separate floating elements from the workspace.

Shadows should never be pure black; they are tinted with the Primary Blue to maintain a cohesive, clean look. Borders are the primary method of separation, ensuring the UI remains sharp and functional.

## Shapes
This design system employs a **Soft** shape language. Standard UI elements like buttons, input fields, and cards use a 0.25rem (4px) corner radius. This provides a modern touch while maintaining the rigid, organized structure expected of a corporate tool. Large containers like dashboard cards may utilize `rounded-lg` (8px) to soften the overall interface without appearing overly "bubbly" or consumer-grade.

## Components
Consistent styling across components ensures the HR system feels like a singular, integrated tool.

*   **Data Tables:** Clean, borderless rows with a 1px Slate 200 divider. Headers are `label-sm` in Slate 500. Row hover states use Slate 50. Use "Zebra striping" only for tables exceeding 20 rows.
*   **Form Inputs:** 40px height for standard inputs. 1px Slate 300 border, turning Primary Blue on focus with a 2px soft outer glow. Labels are positioned above the field in `label-md`.
*   **Status Badges:** Pill-shaped with a light background and dark text (e.g., Success is Emerald 100 background with Emerald 800 text). Use for "Employment Status" or "Application Phase."
*   **Dashboard Metric Cards:** Focused on a single "Hero" number (Headline-LG) with a secondary label and a small trend sparkline. Include a 1px border and a subtle Level 1 shadow.
*   **Buttons:** Primary buttons are solid Primary Blue or Emerald Green. Secondary buttons use a ghost style with a 1px Slate 300 border. All buttons use `label-md` weight.
*   **Breadcrumbs:** Essential for deep-hierarchical HR navigation. Use Slate 400 for inactive links and Primary Blue for the current page.