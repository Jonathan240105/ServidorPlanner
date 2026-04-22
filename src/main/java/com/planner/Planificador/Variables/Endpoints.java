package com.planner.Planificador.Variables;

public final class Endpoints {

	private Endpoints() {
	}

	public static final class Usuario {
		public static final String encabezadoUsuario = "/usuarios";
		public static final String getUsuarioPorId = "/{id}";
		public static final String iniciarSesion = "/iniciarSesion";

	}

	public static final class Workspace {
		public static final String encabezadoWorkspace = "/workSpaces";
		public static final String getWorkspacesUsuario = "/{id}";
		public static final String eliminarWorkspace = "/delete/{id}";
		public static final String nuevoWorkspace = "/nuevo";
		public static final String buscarWorkspace = "/buscar/{idUsuario}";
		public static final String actualizarWorkspace = "/actualizar/{id}";
	}

	public static final class Lista {
		public static final String encabezadoLista = "/lista";
		public static final String getListasWorkspace = "/{id}";
		public static final String eliminarLista = "/delete/{id}";
		public static final String nuevaLista = "/nuevo";
		public static final String actualizarLista = "/actualizar/{id}";
	}

	public static final class Tarea {
		public static final String encabezadoTarea = "/tareas";
		public static final String getTareasLista = "/{id}";
		public static final String eliminarTarea = "/delete/{id}";
		public static final String nuevaLista = "/nuevo";
		public static final String buscarTareasPorTitulo = "/buscar/{idLista}";
		public static final String moverTareaLista = "/{idTarea}/mover/{idNuevaLista}";
		public static final String actualizarTarea = "/actualizar/{id}";
	}

	public static final class Subtarea {
		public static final String encabezadoSubtarea = "/subtareas";
		public static final String getSubtareaTarea = "/{idSubtarea}";
		public static final String eliminarSubtarea = "/delete/{id}";
		public static final String nuevaSubtarea = "/nuevo";
		public static final String actualizarSubtarea = "/actualizar/{id}";
	}

	public static final class Comentario {
		public static final String encabezadoComentario = "/comentarios";
		public static final String getComentariosTarea = "/{id}";
		public static final String eliminarComentario = "/delete/{id}";
		public static final String nuevoComentario = "/nuevo";
	}

}
