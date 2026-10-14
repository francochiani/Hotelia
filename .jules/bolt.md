## 2026-10-14 - Optimize huespedDAO.guardar
**Learning:** Found an anti-pattern in `huespedDAO.guardar` where `objetoconexion.establecerConexion()` was being called repeatedly to create `CallableStatement` instances. This opens multiple network connections unnecessarily.
**Action:** Always reuse a single database connection per transaction or logical unit of work using a try-with-resources block.
