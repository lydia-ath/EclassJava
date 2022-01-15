package com.example.app.resource;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

import com.example.app.dao.EvaluationDAO;
import com.example.app.dao.JPAUtil;
import com.example.app.daoImpl.EvaluationDAOImpl;
import com.example.app.domain.Evaluation;
import com.example.app.school.SchoolException;
import com.example.app.util.HttpError;

import javax.ws.rs.core.Response.Status;

@XmlRootElement
@Path("evaluations")
public class EvaluationResource {
	
	@Context
	UriInfo uriInfo;
	
	@GET
	@Path("/all")
	//@Produces("application/xml")
	public List<Evaluation> listAllEvaluations(){
		
		EvaluationDAO evaluationdao = new EvaluationDAOImpl();
		List<Evaluation> evaluations = evaluationdao.findAll();
		
		return evaluations;
		 
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEvaluation(EvaluationInfo evaluationinfo) {
		
		EntityManager em = getEntityManager();

		Evaluation evaluation = evaluationinfo.getEvaluation(em);
		
		EvaluationDAO evaluationdao = new EvaluationDAOImpl();
		evaluationdao.save(evaluation);

		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		URI newEvaluationUri = ub.path(Long.toString(evaluation.getEvaluationId())).build();

		em.close();

		return Response.created(newEvaluationUri).build();
	}
	
	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();

	}
	
	/**Delete evaluation with java rest*/
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteEvaluation(EvaluationInfo evaluationinfo){
		
		EntityManager em = getEntityManager();
		
		Evaluation evaluation = evaluationinfo.getEvaluation(em);
		
		EvaluationDAO evaluationdao = new EvaluationDAOImpl();
		//ReturnService service = new ReturnService(em);
		Response response= null;
		try {
			evaluationdao.delete(evaluation);
			//service.returnItem(evaluationId);
			response = Response.ok().build();
		} catch (SchoolException e) {
			
			HttpError error = HttpError.httpNotFoundError(e.getMessage());
			response = Response.status(Status.NOT_FOUND).entity(Entity.entity(error, MediaType.APPLICATION_JSON))
					.build();
			
		}
		
		em.close();
		
		return response;
		
	}

}

